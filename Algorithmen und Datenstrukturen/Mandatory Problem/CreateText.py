#  download books from https://libraryofbabel.info
#  save files into 'Original Texts'

import os

open("text.txt", 'w+')  # create target-file

# iterate through all files in source directory
for file_name in os.listdir("Original Texts"):
    if file_name.endswith(".txt") and file_name != "text.txt":

        # read and save content of a sourcefile
        with open("Original Texts/" + file_name, 'r+') as file:
            lines = file.readlines()

        # save lines of content in target-file without separators
        with open("text.txt", 'a') as file:
            for line in lines:
                if "Book Location" not in line:
                    file.write(line.removesuffix("\n"))
