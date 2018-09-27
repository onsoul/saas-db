package com.hitler.common.dto.support;

import java.io.Serializable;

import com.hitler.common.repository.Sorter;

/**
 * @author 传递
 * 
 * 客户端表格（JQuery Datatable）分页传递参数 通用对象
 * 
 */
public abstract class PaginationAoDataDTO<PK extends Serializable> extends GenericDTO<PK> {
	private static final long serialVersionUID = 2572279813924674692L;
	
	/**
	 * 请求次数
	 */
	private String sEcho;
	
	/**
	 * 显示的列数
	 */
	private Integer iColumns;
	
	/**
	 * 服务器返回后显示的列名，格式  , , ,....
	 */
	private String sColumns;	
	
	/**
	 * 显示开始行数
	 */
	private Integer iDisplayStart;
	
	/**
	 * 每页显示行数
	 */
	private Integer iDisplayLength;
	
	/**
	 * 当前排序列索引（从0开始）
	 */	
	private Integer iSortCol_0;
	
	/**
	 * 当前排序列排序方向
	 */
	private String sSortDir_0;

	/**
	 * 当前排序列个数
	 */
	private Integer iSortingCols;
	
	/*未知参数，暂未用到
	mDataProp_0: id
	sSearch_0:
	bRegex_0: false
	bSearchable_0: true
	bSortable_0: false
	mDataProp_1: firstName
	sSearch_1:
	bRegex_1: false
	bSearchable_1: true
	bSortable_1: true
	mDataProp_2: lastName
	sSearch_2:
	bRegex_2: false
	bSearchable_2: true
	bSortable_2: true
	mDataProp_3: id
	sSearch_3:
	bRegex_3: false
	bSearchable_3: true
	bSortable_3: true
	sSearch:
	bRegex: false
	*/
	
	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiColumns() {
		return iColumns;
	}

	public void setiColumns(Integer iColumns) {
		this.iColumns = iColumns;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Integer getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(Integer iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public Integer getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(Integer iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	/**
	 * 当前显示页索引（从0开始） 
	 * @return
	 */
	public Integer getDisplayIndex(){
		return iDisplayStart/iDisplayLength;
	}

	/**
	 * 当前排序列字符串，包含排序方向
	 * @return
	 */
	public String getSortColStr(){
		if (sColumns != null && sColumns.length() > 1){
			String[] cols = sColumns.split(",");
			if (cols.length >0 && cols.length > iSortCol_0){
				if (cols[iSortCol_0].trim().length() >0){
					return " order by " + cols[iSortCol_0] + " " + getsSortDir_0();
				}
			}
		}
		return "";
	}
	
	/**
	 * 当前排序对象
	 * @return
	 */
	public Sorter getSorter(){
		if (sColumns != null && sColumns.length() > 1){
			String[] cols = sColumns.split(",");
			if (cols.length >0 && cols.length > iSortCol_0){
				if (cols[iSortCol_0].trim().length() >0){
					Sorter sorter = new Sorter(getsSortDir_0(), cols[iSortCol_0]);
					return sorter;
				}
			}
		}
		return null;
		
	}
	
	
}
