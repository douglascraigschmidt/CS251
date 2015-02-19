#Expression Tree Mobile Cloud Application

The expression tree application is a calculator. In this version of the app, functionality is divided into two parts: a user-facing GUI interface in the form of an Android mobile app and a Java web servlet that handles computations.

The reason for splitting the application is twofold. First, we wanted to use Java 8 features, which Android does not support, but the Oracle stack does. Thus we decided to move business logic into the cloud. The second purpose is to demonstrate the ease with which a well-structured, reusable application can be repurposed to different scenarios. The original application was not network enabled, but adapting it to a mobile cloud format required minimal code changes because the code was well encapsulated.

##Expression Tree Protocol

Here we'll briefly document the method by which the server and the Android application communicate. The ExpressionTree server is not a REST service by strict definition, but it is vauguely Restful. The ExpressionTree server has only one endpoint: "/ExpressionTreeServer". This endpoint takes two GET parameters:

	?input=[input string]	 The command string typed by the user that should be interpreted as a command.

	?clientID=[ID #]	 If the client has state stored on the server, it should provide its ID so that the server knows which state to access. If it is not found, or if the client does not provide an ID, then the server will create new state for the client and return a random clientID in the response for future use.

The server will respond with a JSON object, structured as follows:

	{ // The result code. Possible values include "ok", "malformed", or "exception"
	  result: "ok",

	  // A client ID that is generated for client that does not yet have an ID.
	  clientID: 1234,

	  // An array of calls to Platform that are used to output results
	  platformCalls: [
		
		// Each platform call has a function name from Platform and a list of parameters to pass to that function
		{ fname: "outputLine", params: [ "test" ] },
		{ fname: "outputMenu", params: [ "numeral", "option", "selection" ] }

	  ]

	  // An error message that might be returned, if status is "exception"
	  message: "Some exception message"
	}

Please note that all members except for "result" are optional.