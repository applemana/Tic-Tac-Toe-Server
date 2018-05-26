all: server client

client: 
	javac -cp ./../../../json-simple-1.1.jar:./../../../httpclient-4.5.5.jar:./../../../httpcore-4.4.9.jar:./../../../commons-logging-1.2.jar: Controller/Tic_Tac_Toe.java

run_client: client
	java -cp ./../../../json-simple-1.1.jar:./../../../httpclient-4.5.5.jar:./../../../httpcore-4.4.9.jar:./../../../commons-logging-1.2.jar:. Controller.Tic_Tac_Toe

clean:
	rm -f ./Controller/Tic_Tac_Toe.class
