<div align="center">
  <br>
  <h1>Virtual file system</h1>
</div>


## 📙 Summary

This project is simulation of the allocation and de-allocation of files and folders using different allocation techniques.

The techniques which simulated in this project:

- **[Contiguous allocation](src/contiguousAllocation.java):**

  In this technique, each file occupies a contiguous set of blocks on the disk. For example, if a file requires n blocks and is given a block b as the starting location, then the blocks assigned to the file will be: *b, b+1, b+2,……b+n-1.* This means that given the starting block address and the length of the file (in terms of blocks required), we can determine the blocks occupied by the file.

  

- **[Indexed allocation](src/indexedAllocation.java):**

  In this scheme, a special block known as the Index block contains the pointers to all the blocks occupied by a file. Each file has its own index block. The ith entry in the index block contains the disk address of the ith file block.

  

- **[Linked allocation](src/indexedAllocation.java):**

  In this scheme, each file is a linked list of disk blocks which need not be contiguous. The disk blocks can be scattered anywhere on the disk.
  The directory entry contains a pointer to the starting and the ending file block. Each block contains a pointer to the next block occupied by the file.

In this program we are not creating actual files and folder, we will just simulate having a series of blocks and these blocks will be allocated to files when created and will be de-allocated when these files are deleted, our virtual file system information like (the files information, the folders information, the allocated blocks and so on) will be saved on files like **contiguousAllocation.txt**, **indexedAllocation.txt** and **linkedAllocation.txt** to be loaded the next time you run the application.



## 💻 Running

Open the terminal or the command line in the directory of our repository and type the following commands:

`cd ./src`

`javac *`

`java Main.java`



## :bulb: Commands 

**After running the application the user will interact with your virtual file system through the following commands:**

 - `CreateFile` :  This command used to create a file and it takes 2 parameters (path of the file which will be created and the size of this file).

    ex: `CreateFile root/file.txt 100`

   In the previous example a file named `file.txt` will be created with 100 KB size under the path `root`.	

   

 - `CreateFolder` :  This command used to create a folder and it takes 1 parameter (path of the folder which will be created).

    ex: `CreateFolder root/folder1`

   In the previous example a folder named `folder1` will be created under the path `root`.

   

-  `DeleteFile` :  This command used to delete a file and it takes 1 parameter (path of the file which will be deleted) but it requires existing this file under the path specified.

    ex: `DeleteFile root/folder1/file.txt`

   In the previous example a file named `folder1` under the path `root` will be deleted.

   

- `DeleteFolder` :  This command used to delete a folder and it takes 1 parameter (path of the folder which will be deleted) but it requires existing this file under the path specified.

   ex: `DeleteFolder root/folder1`

   In the previous example a folder named `folder1` under the path `root` will be deleted.

   

- `DisplayDiskStatus` :  This command used to display the status of your driver and this status should contain empty space, allocated space, empty blocks in the disk and allocated blocks in the disk . It takes no parameters.

    

-  `DisplayDiskStructure` :  This command will display the files and folders in your system file in a tree structure. It takes no parameters.



## :dart: ​Clone the project

`$ git clone https://github.com/MarwanGalal746/Virtual-file-system`



## :busts_in_silhouette:  Contributers

[Eyad Youssef](https://github.com/Eyadzz), [Abdelrahman Ali](https://github.com/abdelrahmanali6), [Abdelrahman Ammar](https://github.com/Abdelrhman-ammar) and [Marwan Galal](https://github.com/MarwanGalal746).
