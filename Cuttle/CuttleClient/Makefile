CLASSPATH=server/src:server/3rdparty/nanohttpd-2.2.0.jar

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

.PHONY=clean
clean:
	find . -type f -name '*.class' -delete
