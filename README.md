# TextIndexer
Small Java app enabling indexing text files and searching words across them

- Test .txt files are located inside src/files
- To run tests, go to src/tests package

Usage example 1 - Creating file only:
- Create a new .txt file inside src/files package manually
- Set the following content: John has found an apple
- Run the app
- Choose option 1 - Indexing text files and type the file name including extension (newFile.txt for example, excluding the prefix: src/files)
- Choose option 2 - Type in the word 'apple'
- You should now see listed the newFile.txt in the next line, as you are searching for files containing the word 'apple'
- Exit the app

Usage example 2 - Creating a folder with two files in it
- Create a folder named testFolder inside src/files
- Inside this folder create file newNestedFile1.txt with the following content: Peter has found an apple
- Inside this folder create file newNestedFile2.txt with the following content: Emma has found an apple
- Run the app
- Choose option 1 - Indexing text files and type the path to the folder (testFolder, without the prefix src/files)
- You should now see that the all files inside this folder are indexed
- Choose option 2 - Type in the world 'apple'
- You should see all files in testFolder containing word 'apple'
- Exit the app
