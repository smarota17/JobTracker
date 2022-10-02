package com.group21.jobTracker.ui.application;

import java.util.Locale;
import java.util.Objects;

import com.group21.jobTracker.backend.data.Jobs;

import com.group21.jobTracker.backend.data.Product;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.vaadin.flow.data.provider.ListDataProvider;

/**
 * Utility class that encapsulates filtering and CRUD operations for
 * {@link Product} entities.
 * <p>
 * Used to simplify the code in {@link SampleCrudView} and
 * {@link SampleCrudLogic}.
 */
public class ApplicationDataProvider extends ListDataProvider<Jobs> {

    /** Text filter that can be changed separately. */
    private String filterText = "";

    public ApplicationDataProvider() {
        super(JobDataService.get().getAllJobs());
    }

    /**
     * Store given product to the backing data service.
     *
     * @param product
     *            the updated or new product
     */
    public void save(Jobs job) {
        final boolean newProduct = job.isNewJob();

        JobDataService.get().updateJob(job);
        if (newProduct) {
            refreshAll();
        } else {
            refreshItem(job);
        }
    }

    /**
     * Delete given product from the backing data service.
     *
     * @param product
     *            the product to be deleted
     */
    public void delete(Jobs job) {
        JobDataService.get().deleteJob(job.getId());
        refreshAll();
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
        //                || passesFilter(job.getJobType(), this.filterText)
        setFilter(job -> passesFilter(job.getJobTitle(), this.filterText));
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
