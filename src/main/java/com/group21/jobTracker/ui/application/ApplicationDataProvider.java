package com.group21.jobTracker.ui.application;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.group21.jobTracker.backend.data.Jobs;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

/**
 * Utility class that encapsulates filtering and CRUD operations for
 * {@link Product} entities.
 * <p>
 * Used to simplify the code in {@link SampleCrudView} and
 * {@link SampleCrudLogic}.
 */
@SuppressWarnings("serial")
public class ApplicationDataProvider extends ListDataProvider<Jobs> {

    /** Text filter that can be changed separately. */
    private String filterText = "";

    public ApplicationDataProvider() {
        super(JobDataService.getJob().getAllJobs());
    }
    
    /**
     * Store given product to the backing data service.
     *
     * @param product
     *            the updated or new product
     * @throws ParseException 
     * @throws NumberFormatException 
     */
    public void save(Jobs job) {
        final boolean newProduct = job.isNewJob();
        User currentUser;
        
		try {
			currentUser = Csv.loadUser(MainLayout.userName);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		}
        
        if (!newProduct) {
            currentUser.deleteExistingJob(job);
        }
        
        currentUser.addJob(job);
        Csv.saveUser(currentUser);
        JobDataService.getInstance();
        refreshAll();
    }

    /**
     * Delete given product from the backing data service.
     *
     * @param product
     *            the product to be deleted
     */
    public void delete(Jobs job) {
    	final boolean newProduct = job.isNewJob();
        User currentUser;
        JobDataService.get().deleteJob(job.getId());
		try {
			currentUser = Csv.loadUser(MainLayout.userName);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		}
        
        if (!newProduct) {
            currentUser.deleteExistingJob(job);
        }
        Csv.saveUser(currentUser);
        
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
