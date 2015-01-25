package com.example.expressiontree.server;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * The context where the expression tree state resides. 
     */
    private TreeOps mTreeOps;
	
    /** 
     * A factory for creating a command. 
     */
    private UserCommandFactory mUserCommandFactory;

    /**
     * Handle to last valid command that was executed. 
     */
    private UserCommand mLastValidCommand;
	
    /** Constructor. */
    @Override
    public void init() throws ServletException {
        mTreeOps = new TreeOps();
        mUserCommandFactory = new UserCommandFactory(mTreeOps);
        UserCommand f = new NullCommand(mTreeOps);
        mLastValidCommand = f;
        Platform.instance(new PlatformProxy());
    }

    /** 
     * This method is called back when input is available.  It
     * implements the Template Method pattern to perform the sequence
     * of steps associated with processing expression tree application
     * commands.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Retrieves input from user.
        String userInputCommand = request.getParameter("input");
        
        if (userInputCommand == null) {
            response.getWriter().println("{result: \"Malformed Request\"}");
        }
        else {
            // URL decode the string
            userInputCommand =
                URLDecoder.decode(userInputCommand,
                                  "UTF-8");
        	
            // Call hook method to make a command based on user input.
            UserCommand command = 
                makeUserCommand(userInputCommand);
	          
            // Call a hook method to execute the command.
            try {
                executeCommand(command);
            }
            catch (Exception e) {
            	response.getWriter().println("{result: \"Exception thrown.\"}");
            	response.flushBuffer();
            	return;
            }
               
            response.getWriter().println(((PlatformProxy) Platform.instance()).reap());
        }
        
        response.flushBuffer();
    }

    /**
     * This hook method makes a command based on the user input.
     */
    private UserCommand makeUserCommand(String userInputCommand) {
        return mUserCommandFactory.makeUserCommand(userInputCommand);
    }

    /** 
     * This hook method executes a command. 
     */
    private void executeCommand(UserCommand command) throws Exception {
        command.execute();
    }
}
