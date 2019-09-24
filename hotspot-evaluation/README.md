# Hotspot-Evaluation Application

## 기능
 - EvaluationCategory(평가항목(예 : 맛, 접근성, 청결도, 친절도 등))
 - Review(가맹점 리뷰)
 - Score(가맹점 평점)

## 기능 별 API 목록
**1 EvaluationCategory API (/category)**
 - 전체 평가항목 조회 (GET /category) 
 - 평가항목 등록 (POST /category)
 - 평가항목 변경 (PUT /category/{evaluationCategoryId})

**2 Review API (/review)**
 - 회원 별 가맹점 리뷰 조회 (GET /review/customer/{customerId}/store/{storeId})
 - 회원 별 전체 리뷰 조회 (GET /review/customer/{customerId})
 - 가맹점 별 전체 리뷰 조회 (GET /review/store/{storeId})
 - 리뷰 등록 (POST /review)
 - 리뷰 변경 (PUT /review/{reviewId})

**3 Score API (/score)**
 - 회원 별 가맹점 평점 조회 (GET /score/customer/{customerId}/store/{storeId})
 - 가맹점 별 평점 조회 (GET /score/store/{storeId})
 - 평점 등록 (POST /score)
 - 평점 변경 (PUT /score/{scoreId})
