/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package cn.pku.coolyr.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * MyEclipse Struts Creation date: 12-27-2015
 * 
 * XDoclet definition:
 * 
 * @struts.form name="fileForm"
 */
public class FileForm extends ActionForm
{
	private static final long serialVersionUID = 1L;
	private FormFile myfile;

	public FormFile getMyfile()
	{
		return myfile;
	}

	public void setMyfile(FormFile myfile)
	{
		this.myfile = myfile;
	}

}