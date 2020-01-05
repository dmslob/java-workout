package com.dmslob.historylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryList {

    private List<String> histories = Collections.synchronizedList(new ArrayList<>());
    private static HistoryList thisInstance = new HistoryList();

    public static HistoryList getInstance() {
        return thisInstance;
    }

    public void addCommand(String command) {
        histories.add(command);
    }

    public Object undoCommand() {
        return histories.remove(histories.size() - 1);
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < histories.size(); i++) {
            result.append(" ");
            result.append(histories.get(i));
            result.append("\n");
        }
        return result.toString();
    }
}
