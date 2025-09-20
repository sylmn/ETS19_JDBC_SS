ETS19_JDBC_SS
Overview: This project demonstrates the basics of working with JDBC in Java using IntelliJ IDEA. It focuses on the three main JDBC components:
- Connection → Establish a connection to the database  
- Statement → Execute SQL queries  
- ResultSet → Retrieve and navigate through query results  
Example Snippet;
java
Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

Statement statement = connection.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY);

ResultSet resultSet = statement.executeQuery("SELECT * FROM locations;");

How to Run ?
Clone this repository: git clone https://github.com/yourusername/ETS19_JDBC_SS.git
Configure your dbUrl, dbUsername, and dbPassword inside the project. Run the tests or main classes via IntelliJ IDEA.

Notes: This is a learning project, not intended for production use. Adjust database credentials before execution.

Auf Deutsch:
Projektübersicht: Dieses Projekt zeigt die Grundlagen von JDBC in Java mit IntelliJ IDEA. Im Mittelpunkt stehen die drei Hauptkomponenten:
-Connection → Aufbau der Datenbankverbindung
-Statement → Ausführung von SQL-Anweisungen
-ResultSet → Abruf und Navigation der Abfrageergebnisse

Zusätzlich wird gezeigt, wie diese Strukturen mit einfachen Test-Tools verbunden werden können.
