CLASSPATH=src:3rdparty/json-20160212.jar:3rdparty/java_websocket.jar
TESTCLASSPATH=$(CLASSPATH):test:3rdparty/junit-4.12.jar:3rdparty/hamcrest-core-1.3.jar

# If the first argument is "run"...
ifeq (run,$(firstword $(MAKECMDGOALS)))
  # use the rest as arguments for "run"
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  # ...and turn them into do-nothing targets
  $(eval $(RUN_ARGS):;@:)
endif

all:
	find . -type f -name '*.java' -exec javac -cp $(CLASSPATH) {} +

run: all
	java -cp $(CLASSPATH) Main $(RUN_ARGS)

.PHONY: test
test: all
	javac -cp $(TESTCLASSPATH) test/*.java

.PHONY: clean
clean:
	find . -type f -name '*.class' -delete

