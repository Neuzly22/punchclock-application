package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.util.JwtUtil;
import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entries")
public class EntryController {

    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getEntriesOfApplicationUser(
        @RequestHeader(name = "Authorization") String token) {
        ApplicationUser user = JwtUtil.parseToken(token);
        return this.entryService.getEntriesOfApplicationUser(user.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry,
        @RequestHeader(name = "Authorization") String token) {
        ApplicationUser user = JwtUtil.parseToken(token);
        entry.setApplicationUser(user);
        return entryService.createEntry(entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        this.entryService.delete(Long.parseLong(id));
    }
}
