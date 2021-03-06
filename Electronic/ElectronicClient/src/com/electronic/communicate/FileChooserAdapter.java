package com.electronic.communicate;

import java.util.ArrayList;

import com.electronic.view.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileChooserAdapter extends BaseAdapter {
	private ArrayList<FileInfo> mFileLists;
	private LayoutInflater mLayoutInflater = null;
	private static ArrayList<String> PPT_SUFFIX = new ArrayList<String>();
	private static ArrayList<String> TXT_SUFFIX = new ArrayList<String>();
	static {
		PPT_SUFFIX.add(".ppt");
		PPT_SUFFIX.add(".pptx");
	}
	static {
		TXT_SUFFIX.add(".txt");
	}
	public FileChooserAdapter(Context context, ArrayList<FileInfo> fileLists) {
		super();
		mFileLists = fileLists;
		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFileLists.size();
	}

	@Override
	public FileInfo getItem(int position) {
		// TODO Auto-generated method stub
		return mFileLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		ViewHolder holder = null;
		if (convertView == null || convertView.getTag() == null) {
			view=mLayoutInflater.inflate(R.layout.boardfilechoosergridview, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}
		else{
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}
		FileInfo fileInfo = (FileInfo) getItem(position);
		holder.tvFileName.setText(fileInfo.getFileName());
		if(fileInfo.isDirectory())
		{
			holder.imgFileIcon.setImageResource(R.drawable.ic_folder);
			holder.tvFileName.setTextColor(Color.GRAY);
		}
		else if(fileInfo.isPPTFile())
		{
			holder.imgFileIcon.setImageResource(R.drawable.ic_ppt);
			holder.tvFileName.setTextColor(Color.RED);
			
		}
		else if(fileInfo.isTxtFile())
		{
			holder.imgFileIcon.setImageResource(R.drawable.ic_txt);
			holder.tvFileName.setTextColor(Color.BLACK);
			
		}
		else
		{
			holder.imgFileIcon.setImageResource(R.drawable.ic_file_unknown);
			holder.tvFileName.setTextColor(Color.GRAY);
		}
		return view;
	}
	static class ViewHolder {
		ImageView imgFileIcon;
		TextView tvFileName;
		public ViewHolder(View view) {
			imgFileIcon=(ImageView)view.findViewById(R.id.imgFileIcon);
			tvFileName=(TextView)view.findViewById(R.id.tvFileName);
			
		}
	}
	enum FileType {
		FILE , DIRECTORY;
	}
	public static class FileInfo {
		private FileType fileType;
		private String fileName;
		private String filePath;
		public FileInfo(String filePath, String fileName, boolean isDirectory) {
			this.filePath = filePath;
			this.fileName = fileName;
			fileType = isDirectory ? FileType.DIRECTORY : FileType.FILE;
		}
		public boolean isPPTFile(){
			if(fileName.lastIndexOf(".") < 0)  //Don't have the suffix 
				return false ;
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
			if(!isDirectory() && PPT_SUFFIX.contains(fileSuffix))
				return true ;
			else
				return false ;
		}
		public boolean isTxtFile()
		{
			if(fileName.lastIndexOf(".") < 0)  //Don't have the suffix 
				return false ;
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
			if(!isDirectory() && TXT_SUFFIX.contains(fileSuffix))
				return true ;
			else
				return false ;
		}
  
		public boolean isDirectory(){
			if(fileType == FileType.DIRECTORY)
				return true ;
			else
				return false ;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		@Override
		public String toString() {
			return "FileInfo [fileType=" + fileType + ", fileName=" + fileName
					+ ", filePath=" + filePath + "]";
		}
	}

}
