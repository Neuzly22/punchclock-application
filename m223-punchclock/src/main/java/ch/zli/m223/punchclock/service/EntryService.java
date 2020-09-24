package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public void delete(Long index) {
        this.entryRepository.deleteById(index);
    }

    public Entry update(Entry entry) {
        System.out.println(entry.getApplicationUser().getId());
        return this.entryRepository.save(entry);
    }

    public List<Entry> getEntriesOfApplicationUser(long id) {
        return this.entryRepository.getEntriesByApplicationUserId(id);
    }
}
