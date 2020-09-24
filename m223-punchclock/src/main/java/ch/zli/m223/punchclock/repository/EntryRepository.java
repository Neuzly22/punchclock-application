package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Entry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> getEntriesByApplicationUserId(@Param("id") long id);
}
