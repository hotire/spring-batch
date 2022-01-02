# Cursor

## MySQL

### AbstractCursorItemReader

~~~java
protected T doRead() throws Exception {
		if (rs == null) {
			throw new ReaderNotOpenException("Reader must be open before it can be read.");
		}

		try {
			if (!rs.next()) {
				return null;
			}
			int currentRow = getCurrentItemCount();
			T item = readCursor(rs, currentRow);
			verifyCursorPosition(currentRow);
			return item;
		}
		catch (SQLException se) {
			throw getExceptionTranslator().translate("Attempt to process next row failed", getSql(), se);
		}
	}
~~~

### FetchSize

- https://cheese10yun.github.io/spring-batch-reader-performance-2/
- https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-implementation-notes.html#ResultSet

### ResultsetRows

- ResultsetRowsStatic
~~~
 Represents an in-memory result set
~~~

- ResultsetRowsStreaming
~~~
fetch size has been set to Integer.MIN_VALUE (rows are read one by one).
~~~
 
### BinaryResultsetReader

### NativeProtocol

init 

~~~java
  protocolEntityClassToBinaryReader.put(Resultset.class, new BinaryResultsetReader(this));
~~~

 

### handleWarnings

- AbstractCursorItemReader
~~~
protected void handleWarnings(Statement statement) throws SQLWarningException,
	SQLException {
		if (ignoreWarnings) {
			if (log.isDebugEnabled()) {
				SQLWarning warningToLog = statement.getWarnings();
				while (warningToLog != null) {
					log.debug("SQLWarning ignored: SQL state '" + warningToLog.getSQLState() + "', error code '"
							+ warningToLog.getErrorCode() + "', message [" + warningToLog.getMessage() + "]");
					warningToLog = warningToLog.getNextWarning();
				}
			}
		}
		else {
			SQLWarning warnings = statement.getWarnings();
			if (warnings != null) {
				throw new SQLWarningException("Warning not ignored", warnings);
			}
		}
	}
~~~
SQLWarning warningToLog = statement.getWarnings(); 에서 에러가 발생함 

