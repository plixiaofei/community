package com.lixiaofei.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    private Boolean showFirst;
    private Boolean showEnd;
    private Integer page;
    private Integer totalPage;
    private List<QuestionDTO> questions = new ArrayList<>();
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if(totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
//        if(page > totalPage) {
//            page = totalPage;
//        }
//        if(page < 1) {
//            page = 1;
//        }
        this.page = page;
        pages.add(page);
        for(int i = 1;i <= 2;i ++) {
            if(page - i > 0) {
                pages.add(0,page - i);
            }
            if(page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        if(pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }
        if(pages.contains(totalPage)) {
            showEnd = false;
        } else {
            showEnd = true;
        }
    }


    public Boolean getShowFirst() {
        return showFirst;
    }

    public void setShowFirst(Boolean showFirst) {
        this.showFirst = showFirst;
    }

    public Boolean getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(Boolean showEnd) {
        this.showEnd = showEnd;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
