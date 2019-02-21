###**Project Description:**
This project can take as parameter any CSV file which will be analyzed. 
In order to make this project was used the CSVReader for parsing the file, which gave the file already splitted.
All the analysis consists of each record's verification:if it matches the column count and all elements which contain commas should be double qouted.
Data which correspond to all criteria are stored in _Record.db_ database in table X.This table contains 10 columns A-J with no constraints and an ID columns as primary key with autoincrement,that matches the sample csv file format.
But records that don't correspond to criteria are stored in _badData.csv_ file.
After the process is finished a log file: _statistic.log_ is created with the main purpose to register the statistic:total number of records, number of  bad records and successful one.
#####**It's important to mention that al these files are stored in _data_ folder from this project!**
###**Setup:**
To parse the csv file, its location should be specified as parameter of _read()_method of CvsFileManager in Main.class.
Also it's possible to change the directory of new created files:_path_ and _name_ of database can be modified in DataBaseManager.class,for log file in LoggerWriter.class and badData file in _write()_ method of CsvFileManager.class;
###**Dependencies:**
Java Version : 1.8.0_201 <br/>
SQLite Version : 3.27.1 <br/>
Another dependencies are included in _pom.xml_ file which will be auto-imported.
###**Run the project:**
1.Download the JDK which was mentioned above;<br\>
2.Open the project with an IDE and specify in Main.class the url of your csv file
(As an example of file are included in data folder: interview.csv)<br\>
3.When project is built , just run the Main.class.After this you can see in console shoe information concerning database creation and if connection was established.
At the end you will see info which were written in log file.
 
