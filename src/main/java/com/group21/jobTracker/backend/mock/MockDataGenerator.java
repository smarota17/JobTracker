package com.group21.jobTracker.backend.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.group21.jobTracker.backend.data.Availability;
import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.Product;

public class MockDataGenerator {
    private static int nextCategoryId = 1;
    private static int nextJobId = 1;
    private static final Random random = new Random(1);
    private static final String categoryNames[] = new String[] {
            "Software Development", "Project/Program/Product Management--Technical", "Business Intelligence", "Human Resources",
            "Operations, IT, & Support Engineering", "Project/Program/Product Management--Non-Tech"};

    private static String[] word1 = new String[] { "The art of", "Mastering",
            "The secrets of", "Avoiding", "For fun and profit: ",
            "How to fail at", "10 important facts about",
            "The ultimate guide to", "Book of", "Surviving", "Encyclopedia of",
            "Very much", "Learning the basics of", "The cheap way to",
            "Being awesome at", "The life changer:", "The Vaadin way:",
            "Becoming one with", "Beginners guide to",
            "The complete visual guide to", "The mother of all references:" };
    
    private static String[] word2 = new String[] { "The art of", "Mastering",
            "The secrets of", "Avoiding", "For fun and profit: ",
            "How to fail at", "10 important facts about",
            "The ultimate guide to", "Book of", "Surviving", "Encyclopedia of",
            "Very much", "Learning the basics of", "The cheap way to",
            "Being awesome at", "The life changer:", "The Vaadin way:",
            "Becoming one with", "Beginners guide to",
            "The complete visual guide to", "The mother of all references:" };


    private static String[] word3 = new String[] { "Software Engineer I",
            "Software Engineer II", "Senior Software Engineer", "Cloud Engineer",
            "mechanical Engineer", "Business Analyst", "Medical Assistant",
            "Web Designer", "Dog Trainer", "President of Sales",
            "Nursing Assistant" };
    
    private static String[] word4 = new String[] { "SwipeWire","SecureSmarter","Dwellsmith",
            "SalePush", "Formonix","Branding","Cloudrevel","Seekingon", "Medicine","Crowdstage",
            "Hiphonic","QuickSpace","MetConnect", "Rentoor","Kiddily", "Jumpsync","Conceptual",
            "VisionSwipe","Tourish","Drivemo","Knowza","Composey","Excursy","InvestSpend","Deductly",
            "SiteDept","Metricsilo","Legal Right","Shipplier","SecuriToday",
            "GameEight","Nutrition","TechTack","Digimail"};

    static List<Category> createCategories() {
        List<Category> categories = new ArrayList<Category>();
        for (String name : categoryNames) {
            Category c = createCategory(name);
            categories.add(c);
        }
        return categories;

    }

    static List<Jobs> createJobs(List<Category> categories) {
        List<Jobs> jobs = new ArrayList<Jobs>();
        for (int i = 0; i < 100; i++) {
            Jobs j = createJob(categories);
            jobs.add(j);
        }

        return jobs;
    }

    private static Category createCategory(String name) {
        Category c = new Category();
        c.setId(nextCategoryId++);
        c.setName(name);
        return c;
    }

    private static Jobs createJob(List<Category> categories) {
        Jobs p = new Jobs();
        p.setId(nextJobId++);
        p.setName(generateName());
        p.setJobTitle(generateTitle());
        p.setCompany(generateCompany());

//        p.setPrice(new BigDecimal((random.nextInt(250) + 50) / 10.0));
//        p.setAvailability(Availability.values()[random.nextInt(Availability
//                .values().length)]);
//        if (p.getAvailability() == Availability.AVAILABLE) {
//            p.setStockCount(random.nextInt(523));
//        }

        p.setCategory(getCategory(categories, 1, 2));
        return p;
    }

    private static Set<Category> getCategory(List<Category> categories,
        int min, int max) {
        int nr = random.nextInt(max) + min;
        HashSet<Category> productCategories = new HashSet<Category>();
        for (int i = 0; i < nr; i++) {
            productCategories.add(categories.get(random.nextInt(categories
                    .size())));
        }

        return productCategories;
    }

    private static String generateName() {
        return word1[random.nextInt(word1.length)] + " "
                + word2[random.nextInt(word2.length)];
    }

    private static String generateTitle() {
        return word3[random.nextInt(word3.length)];
    }

    private static String generateCompany() {
        return word4[random.nextInt(word4.length)];
    }

}
