package com.example.expressiontree.server;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.expressiontree.model.ServerResponse;
import com.google.gson.Gson;

/**
 * @class ServerInputHandler
 *
 * @brief Handles GET requests coming into the server
 * @see
 */
@WebServlet("/")
public class ServerInputHandler extends HttpServlet {
	/**
	 * Version number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Classifies the Handler as verbose.
	 */
	static boolean mVerboseField;

	/**
	 * A factory for making user commands. Each factory contains it's own
	 * context, where the expression tree state resides. Each client gets their
	 * own.
	 */
	Map<Long, UserCommandFactory> mClientStates;
	
	/**
	 * The next client ID we're going to assign
	 */
	long mNextID = 0;

	/** Constructor. */
	@Override
	public void init() throws ServletException {
		mClientStates = new HashMap<>();
		Platform.instance(new PlatformProxy());
	}

	/**
	 * This method is called back when input is available. It implements the
	 * Template Method pattern to perform the sequence of steps associated with
	 * processing expression tree application commands.
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		// Retrieves input from user.
		String userInputCommand = request.getParameter("input");

		if (userInputCommand == null) {
			response.getWriter().println("{result: \"malformed\"}");
		} else {
			// URL decode the string.
			userInputCommand = URLDecoder.decode(userInputCommand, "UTF-8");
				
			// See if they gave us an ID
			String id = request.getParameter("clientID");
			boolean foundId = false;
			UserCommandFactory factory;
			
			// If they did, and it's in our map, use that state
			if (id != null && mClientStates.containsKey(Long.parseLong(id))) {
				factory = mClientStates.get(Long.parseLong(id));
				foundId = true;
			}
			else
				factory = new UserCommandFactory (new TreeOps());
			
			// Make the command
			UserCommand command = factory.makeUserCommand(userInputCommand);

			// Call a hook method to execute the command.
			try {
				executeCommand(command);

				command.printValidCommands(true);
			} catch (Exception e) {
				response.getWriter().print(
						"{result: \"exception\", message: \"" + e.getMessage()
								+ "\"}");
				response.flushBuffer();
				return;
			}
			
			ServerResponse resp = ((PlatformProxy) Platform.instance()).reap();
			
			if (!foundId) {
				resp.setID(mNextID);
				mClientStates.put(mNextID, factory);
				++mNextID;
			}
			
			// Convert the Server Response into JSON using GSON and write it out.
			Gson gson = new Gson();
			String json = gson.toJson(resp);
			response.getWriter().println(json);
		}

		response.flushBuffer();
	}

	/**
	 * This hook method executes a command.
	 */
	private void executeCommand(UserCommand command) throws Exception {
		command.execute();
	}
}
