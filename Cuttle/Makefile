CLASSPATH=src:test:3rdparty/junit-4.12.jar:3rdparty/hamcrest-core-1.3.jar


all:
	find . -type f -name '*.java' -exec javac {} +

run: all
	java -cp $(CLASSPATH) Main

.PHONY: test
test: all
	javac -cp $(CLASSPATH) test/*.java

.PHONY: doc
doc:
	cd doc && lualatex doc
	cd doc && lualatex doc
	cd doc && bibtex doc
	cd doc && lualatex doc
	cd doc && lualatex doc

.PHONY: clean
clean:
	find . -type f -name '*.class' -delete
	rm -f saida.txt
	rm -f doc/*.aux
	rm -f doc/*.lof
	rm -f doc/*.log
	rm -f doc/*.lot
	rm -f doc/*.fls
	rm -f doc/*.out
	rm -f doc/*.toc
	rm -f doc/*.bbl
	rm -f doc/*.bcf
	rm -f doc/*.blg
	rm -f doc/*.brf
	rm -f doc/*.dvi
	rm -f doc/*.pdf

