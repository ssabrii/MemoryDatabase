package com.developer.sunmaungoo.memorydb;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Grid {

	private List<String> columns;
	
	private List<List<String>> rows;
	
	public Grid() {
		
		columns = new ArrayList<String>();
		
		rows = new ArrayList<List<String>>();
		
	}
	
	/**
	 * 
	 * @param columnName non-duplicate , non-null
	 * @return
	 */
	public boolean addColumn(String columnName) {
		
		if(columnName==null) {
			return false;
		}
		
		columnName = columnName.trim();
		
		if(columns.contains(columnName)) {
			return false;
		}
		
		return columns.add(columnName);
	}
	
	/**
	 * 
	 * @param row non-null, same size as columns
	 * @return
	 */
	public boolean addRow(List<String> row) {
		
		if(row==null) {
			return false;
		}
		
		if(columns.size()!=row.size()) {
			return false;
		}
		
		return rows.add(row);

	}
	
	public List<List<String>> getRows(List<String> columnNames){
		
		List<Integer> indexes = getIndexes(columnNames);
		
		if(indexes.size()==0) {
			throw new IllegalArgumentException("Please provide valid column name");
		}
		
		if(indexes.size()!=columnNames.size()) {
			throw new IllegalArgumentException("Not all column name provided are found");
		}
		
		List<List<String>> displayRow = new ArrayList<List<String>>();
		
		for(int rowIndex=0;rowIndex<rows.size();rowIndex++) {
			
			List<String> row = new ArrayList<String>();

			List<String> currentRow = rows.get(rowIndex);

			for(int columnIndex=0;columnIndex<currentRow.size();columnIndex++) { 		
				
				if(isSelectRow(columnIndex,indexes)) {
					
					row.add(currentRow.get(columnIndex));
					
				}
			}
			
			displayRow.add(row);
		}
		
		return displayRow;
	}
	
	/**
	 * Get all rows
	 * @return
	 */
	public List<List<String>> getRows(){
		return rows;
	}
	
	private boolean isSelectRow(int columnIndex,
			List<Integer> selectIndexes) {	
		
		return selectIndexes.contains(columnIndex);
	}
	
	/**
	 * 
	 * @param columnNames
	 * @return non-null indexes of column name
	 */
	private List<Integer> getIndexes(List<String> columnNames){
		
		final List<Integer> indexes = new ArrayList<Integer>();
		
		if(columnNames==null) {
			return indexes;
		}
		
		columnNames.forEach(new Consumer<String>() {

			public void accept(String columnName) {
				
				for(int i=0;i<columns.size();i++) {
					
					String currentColumn = columns.get(i);
					
					if(currentColumn.equals(columnName)) {
						
						indexes.add(i);
						
					}
				}
			}
		});
		
		return indexes;
	}
}
