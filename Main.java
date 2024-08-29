import java.util.*;

import Alpha.File;
class Directory
{
    private String name;
    private Map<String, Directory> directories;
    private Map<String, File> files;
    private RecycleBin recycleBin;
    public Directory(String name)
    {
        this.name=name;
        directories= new HashMap<>();
        files=new HashMap<>();
        recycleBin=new RecycleBin();
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void addDirectory(Directory directory)
    {
        directories.put(directory.getName(),directory);
    }
    public void addFile(File file)
    {
        files.put(file.getName(),file);
    }
    public Directory getDirectory(String name)
    {
        return directories.get(name);
    }
    public File getFile(String name)
    {
        return files.get(name);
    }
    public Directory removeDirectory(String name)
    {
        Directory directory=directories.get(name);
        recycleBin.addDirectory(directory);
        return directories.remove(name);
    }
    public File removeFile(String name)
    {
        File file=files.get(name);
        recycleBin.addFile(file);
        return files.remove(name);
    }
    public void renameDirectory(String oldName,String newName)
    {
        Directory dir=directories.remove(oldName);
        if(dir != null)
        {
            dir.setName(newName);
            directories.put(newName,dir);
        }
    }
    public void renameFile(String oldName, String newName)
    {
        File file = files.remove(oldName);
        if(file!=null)
        {
            file.setName(newName);
            files.put(newName,file);
        }
    }
    public void listContents(String indent)
    {
        for(Directory dir : directories.values())
        {
            System.out.println(indent+"Directory :  "+dir.getName());
            dir.listContents(indent+"              ");
        }
        for(File file: files.values())
        {
            System.out.println(indent+"File : "+file.getName());
        }
    }

    public void restoreDirectory(String directoryName1) {
        Directory dir=recycleBin.getDirectory(directoryName1);
        if(dir!=null)
        {
            directories.put(dir.getName(),dir);
            System.out.println("Directory is restored");
        }
    }
    public void restoreFile(String fileName) {
        File file=recycleBin.getFile(fileName);
        if(file!=null)
        {
            files.put(file.getName(),file);
            System.out.println("File is restored");
        }
    }
    public Directory findDirectory(String path) {
        // Split the path into parts
        String[] dirs = path.split("/");

        // Start from the current directory (this)
        Directory current = this;

        for (String dir : dirs) {
            // Ignore empty strings (due to leading or multiple slashes)
            if (!dir.isEmpty()) {
                // Traverse to the next directory
                current = current.getDirectory(dir);
                if (current == null) {
                    return null;  // Path is invalid, directory does not exist
                }
            }
        }
        return current;
    }

}
class RecycleBin
{
    private Map<String , Directory> deletedDirectories;
    private Map<String , File> deletedFiles;
    public RecycleBin()
    {
        this.deletedDirectories = new HashMap<>();
        this.deletedFiles = new HashMap<>();
    }
    public void addDirectory(Directory dir)
    {
        deletedDirectories.put(dir.getName(),dir);
    }
    public void addFile(File file)
    {
        deletedFiles.put(file.getName(),file);
    }
    public Directory getDirectory(String name)
    {
        return deletedDirectories.remove(name);
    }
    public File getFile(String name)
    {
        return deletedFiles.remove(name);
    }
}
class Main
{
    public static void case1()
    {

    }
    public static void main(String ... args)
    {
        Scanner scanner = new Scanner(System.in);
        Directory fs= new Directory("main");

        while(true)
        {
            System.out.println("Welcome Mahi Eco-System");
            System.out.println("|=========================================================|");
            System.out.println("|   1.Create a Directory                                  |");
            System.out.println("|   2.Create a File                                       |");
            System.out.println("|   3.List of Dictionaries and Files                      |");
            System.out.println("|   4.Change the file content                             |");
            System.out.println("|   5.Rename the Directory                                |");
            System.out.println("|   6.Rename the File                                     |");
            System.out.println("|   7.Delete the Directory                                |");
            System.out.println("|   8.Delete the File                                     |");
            System.out.println("|   9.Restore the File                                    |");
            System.out.println("|   10.Restore the Directory                              |");
            System.out.println("|   11.Create a Directory in Directory                    |");
            System.out.println("|   12.Create Directory or File with Custom Path          |");
            System.out.println("|   13.Dynamic List of Dictionaries                       |");
            System.out.println("|   14.Dynamic Content of File                            |");
            System.out.println("|   15.Exit                                               |");
            System.out.println("|=========================================================|");
            System.out.println("Enter your choice:");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch(choice)
            {
                case 1:
                    System.out.print("Enter the Directory Name : ");
                    String name =scanner.nextLine();
                    fs.addDirectory(new Directory(name));
                    break;
                case 2:
                    System.out.println("Enter the File Name : ");
                    String fileName=scanner.nextLine();
                    System.out.println("Enter the Content : ");
                    String content=scanner.nextLine();
                    System.out.print("In Which folder we need to save this file");
                    String path=scanner.nextLine();
                    fs.addFile(new File(fileName,content));
                    break;
                case 3:
                    fs.listContents(" ");
                    break;
                case 4:
                    System.out.println("Enter the File Name : ");
                    String FileName=scanner.nextLine();
                    System.out.println("Enter the  new Content : ");
                    String newContent=scanner.nextLine();
                    File k=fs.getFile(FileName);
                    k.setContent(newContent);
                    break;
                case 5:
                    System.out.println("Enter the Directory Name : ");
                    String oldName=scanner.nextLine();
                    System.out.println("Enter the new Directory Name : ");
                    String newName=scanner.nextLine();
                    fs.renameDirectory(oldName,newName);
                    break;
                case 6:
                    System.out.println("Enter the File Name : ");
                    String oldFileName=scanner.nextLine();
                    System.out.println("Enter the new File Name : ");
                    String newFileName=scanner.nextLine();
                    fs.renameFile( oldFileName, newFileName);
                    break;
                case 7:
                    System.out.println("Enter the Directory Name : ");
                    String directoryName=scanner.nextLine();
                    fs.removeDirectory(directoryName);
                    break;
                case 8:
                    System.out.println("Enter the File Name : ");
                    String fileName1=scanner.nextLine();
                    fs.removeFile(fileName1);
                    break;
                case 10:
                    System.out.println("Enter the Directory Name : ");
                    String directoryName1=scanner.nextLine();
                    fs.restoreDirectory(directoryName1);
                    break;
                case 9:
                    System.out.println("Enter the File Name : ");
                    String fileName2=scanner.nextLine();
                    fs.restoreFile(fileName2);
                    break;
                case 15:
                    System.out.println("Successfully Closed The Console");
                    System.exit(0);
                    break;
                case 11:
                    System.out.println("Enter the Directory Name : ");
                    String directoryName2=scanner.nextLine();
                    System.out.println("Enter the new-sub-Directory Name : ");
                    String newSubDirectoryName=scanner.nextLine();
                    Directory d1=fs.getDirectory(directoryName2);
                    d1.addDirectory(new Directory(newSubDirectoryName));
                    break;
                case 12:
                    System.out.println("Enter the path where to you need to store ");
                    String path2=scanner.nextLine();
                    Directory dir2=fs.findDirectory(path2);
                    if(dir2!=null)
                    {
                        System.out.println("Welcome to "+dir2.getName());
                        System.out.println("|---------------------------------------------------------|");
                        System.out.println(" 1. Create a Directory in "+dir2.getName()+"             ");
                        System.out.println(" 2. Create a File in "+dir2.getName()+"                  ");
                        System.out.println("|---------------------------------------------------------|");
                        System.out.println("Enter your choice:");
                        choice=scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                System.out.print("Enter the Directory Name : ");
                                String directoryName3=scanner.nextLine();
                                dir2.addDirectory(new Directory(directoryName3));
                                break;
                            case 2:
                                System.out.println("Enter the File Name : ");
                                String fileName3=scanner.nextLine();
                                System.out.println("Enter the Content : ");
                                String content3=scanner.nextLine();
                                dir2.addFile(new File(fileName3,content3));
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }

                    }
                    else{
                        System.out.println("Directory not found");
                    }
                    break;
                case 13:
                    System.out.println("Enter the Path where to you want to see : ");
                    String path3=scanner.nextLine();
                    Directory dir3=fs.findDirectory(path3);
                    if(dir3!=null)
                    {
                        System.out.println("Welcome to "+dir3.getName());
                        dir3.listContents("");
                    }
                    else{
                        System.out.println("Directory not found");
                    }
                    break;
                case 14:
                    System.out.println("Enter the Path where to you want to see : ");
                    String path4=scanner.nextLine();
                    Directory dir4=fs.findDirectory(path4);
                    if(dir4!=null)
                    {
                        System.out.println("Welcome to "+dir4.getName());
                        System.out.println("Enter The File Name : ");
                        String fileName4=scanner.nextLine();
                        File f1=dir4.getFile(fileName4);
                        if(f1!=null) {
                            System.out.println(fileName4+"'s Content");
                            System.out.println(f1.getContent());
                        }
                        else{
                            System.out.println("file not found");
                        }
                    }
                    else{
                        System.out.println("Directory not found");
                    }
                    break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
            }
        }
    }
}
