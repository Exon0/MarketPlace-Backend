package tn.workbot.coco_marketplace.services.interfaces;

import tn.workbot.coco_marketplace.entities.Review;

import java.util.List;

public interface ReviewInterface {

    List<Review> getAllReviews();
    Review getReviewById(Long id);
    void addReview(Review review);
    void updateReview(Review review);
    void deleteReview(Long id);
}