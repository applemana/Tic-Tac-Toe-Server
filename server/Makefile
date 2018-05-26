all: run_server

server: main.c computer_logic.c
	gcc -o server main.c computer_logic.c -ljansson -lulfius
	
run_server: server
	LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib ./server

copy_needed_libs:
	mkdir -p libs
	cp /usr/local/lib/libjansson.so.4 ./libs/
	cp /usr/local/lib/libulfius.so.2.3 ./libs/
	cp /usr/local/lib/libyder.so.1.3.3 ./libs/
	cp /usr/local/lib/liborcania.so.1.2.3 ./libs/
	cp /usr/lib/libyder.so.1.2 ./libs/
	cp /usr/lib/libyder.so.1.2.0 ./libs/
	cp /usr/local/lib/libmicrohttpd.so.12 ./libs/

docker_make: copy_needed_libs server
	docker build -t ttt-server .

run_docker: docker_make
	 docker run -i -t -p 1234:1234 ttt-server:latest

CONTAINER=$(shell docker ps -a -q)
clean:
	rm -rf ./server ./libs
	docker stop $(CONTAINER)
	docker rm $(CONTAINER)

