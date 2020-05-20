package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    ArrayList<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();
    int counter = 1;
   @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry savedTimeEntry = new TimeEntry(counter ++, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryList.add(savedTimeEntry);

        return savedTimeEntry;

    }

    @Override
    public  TimeEntry find(long id) {
       for (int i=0; i <timeEntryList.size(); i++){
           if (timeEntryList.get(i).getId() == id)
                return timeEntryList.get(i);
       }
        return null;
    }
    @Override
    public List<TimeEntry> list() {
        return timeEntryList;
    }
    @Override
    public TimeEntry update(Object id, TimeEntry timeEntry) {

        TimeEntry updatedEntry = new TimeEntry(
                (long)id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        if (this.find((long)id)!=null) {
            this.delete(id);
            timeEntryList.add(updatedEntry);
            return updatedEntry;
        }


       // timeEntryList.replace(id, updatedEntry);
        return null;
    }

    @Override
    public void delete(Object id) {
       /* for (int i=0; i <=timeEntryList.size(); i++){
            if (timeEntryList.get(i).getId() == id)
                timeEntryList.remove(i);
        }

*/

      Integer temp = null;
       for (int i=0; i < timeEntryList.size(); i++){
            if (timeEntryList.get(i).getId() == id)
                temp = i;
        }
       if (temp != null) {
           timeEntryList.remove((int)temp);
       }


    }

}
