all:
	make -C CuttleServer
	make -C CuttleClient

run: all
	make -C CuttleServer run & make -C CuttleClient run

.PHONY: clean
clean:
	make -C CuttleServer clean
	make -C CuttleClient clean
