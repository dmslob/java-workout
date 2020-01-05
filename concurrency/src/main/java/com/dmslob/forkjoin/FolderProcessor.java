package com.dmslob.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serialVersionUID = 1L;

    private final String folderFullPath;
    private final String filesExtension;

    public FolderProcessor(String folderFullPath, String filesExtension) {
        this.folderFullPath = folderFullPath;
        this.filesExtension = filesExtension;
    }

    @Override
    protected List<String> compute() {
        List<String> folderFiles = new ArrayList<>();
        // FolderProcessor tasks to store the subtasks that are going to process the subfolders stored in the folder
        List<FolderProcessor> tasks = new ArrayList<>();

        File file = new File(folderFullPath);
        File folderContent[] = file.listFiles();
        // For each element in the folder, if there is a subfolder, create a new FolderProcessor object
        // and execute it asynchronously using the fork() method.
        if (folderContent != null) {
            for (int i = 0; i < folderContent.length; i++) {
                File currentFile = folderContent[i];
                String fileAbsolutePath = currentFile.getAbsolutePath();

                if (currentFile.isDirectory()) {
                    FolderProcessor task = new FolderProcessor(fileAbsolutePath, filesExtension);
                    task.fork();
                    tasks.add(task);
                } else {
                    // Otherwise, compare the extension of the file with the extension you are looking for using the
                    // checkFile() method and, if they are equal, store the full path of the file in the list of
                    // strings declared earlier.
                    if (checkFile(folderContent[i].getName())) {
                        folderFiles.add(fileAbsolutePath);
                    }
                }
            }
        }
        // If the list of the FolderProcessor subtasks has more than 50 elements,
        // write a message to the console to indicate this circumstance.
        if (tasks.size() > 50) {
            System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
        }
        // add to the list of files the results returned by the subtasks launched by this task.
        addResultsFromTasks(folderFiles, tasks);

        return folderFiles;
    }

    // For each task stored in the list of tasks, call the join() method that will wait for its
    // finalization and then will return the result of the task.
    // Add that result to the list of strings using the addAll() method.
    private void addResultsFromTasks(List<String> folderFiles, List<FolderProcessor> tasks) {
        for (FolderProcessor task : tasks) {
            folderFiles.addAll(task.join());
        }
    }

    private boolean checkFile(String fileName) {
        return fileName.endsWith(filesExtension);
    }
}
