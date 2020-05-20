package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntriesRepo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created = this.timeEntriesRepo.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry found = this.timeEntriesRepo.find(timeEntryId);
        if(found==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(found);
        }
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @GetMapping("time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList=this.timeEntriesRepo.list();
        return ResponseEntity.status(HttpStatus.OK).body(timeEntryList);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntryupdated = this.timeEntriesRepo.update(timeEntryId,expected);
        if (timeEntryupdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(timeEntryupdated);
        }
        return ResponseEntity.status(HttpStatus.OK).body(timeEntryupdated);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long timeEntryId) {
        this.timeEntriesRepo.delete(timeEntryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
