package com.group21.jobTracker.ui.jobBoard;

import java.util.Locale;
import java.util.Objects;

import com.group21.jobTracker.backend.data.Jobs;

import com.group21.jobTracker.backend.DataService;
import com.vaadin.flow.data.provider.ListDataProvider;

/**
 * Utility class that encapsulates filtering and CRUD operations for
 * {@link Product} entities.
 * <p>
 * Used to simplify the code in {@link SampleCrudView} and
 * {@link SampleCrudLogic}.
 */
public class JobDataProvider extends ListDataProvider<Jobs> {

    /** Text filter that can be changed separately. */
    private String filterText = "";

    public JobDataProvider() {
        super(DataService.get().getAllJobs());
    }

    /**
     * Sets the filter to use for this data provider and refreshes data.
     * <p>
     * Filter is compared for product name, availability and category.
     *
     * @param filterText
     *            the text to filter by, never null
     */
    public void setFilter(String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null.");
        if (Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim().toLowerCase(Locale.ENGLISH);

        setFilter(job -> passesFilter(job.getJobTitle(), this.filterText)
                || passesFilter(job.getJobTitle(), this.filterText));
    }

    @Override
    public Integer getId(Jobs job) {
        Objects.requireNonNull(job,
                "Cannot provide an id for a null product.");

        return job.getId();
    }

    private boolean passesFilter(Object object, String filterText) {
        return object != null && object.toString().toLowerCase(Locale.ENGLISH)
                .contains(filterText);
    }
}
