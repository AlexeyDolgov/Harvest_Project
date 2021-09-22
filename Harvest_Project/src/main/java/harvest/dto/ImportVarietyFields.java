package harvest.dto;

import java.io.Serializable;
import java.util.Map;

public class ImportVarietyFields implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filename;
	private Integer startRow;
	private Integer endRow;
	private Map<String, Integer> columnFields;

	public ImportVarietyFields() {
	}

	public ImportVarietyFields(String filename, Integer startRow, Integer endRow, Map<String, Integer> columnFields) {
		this.filename = filename;
		this.startRow = startRow;
		this.endRow = endRow;
		this.columnFields = columnFields;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Map<String, Integer> getColumnFields() {
		return columnFields;
	}

	public void setColumnFields(Map<String, Integer> columnFields) {
		this.columnFields = columnFields;
	}
}
