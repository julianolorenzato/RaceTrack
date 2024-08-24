SRC_DIR = src
BUILD_DIR = build
MAIN_CLASS = RaceTrack

# Compile the Java files and place the .class files in the build directory
build:
	mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SRC_DIR)/*.java

# Run the program using the compiled classes
run: build
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Clean the build directory by removing all .class files
clean:
	rm -rf $(BUILD_DIR)
