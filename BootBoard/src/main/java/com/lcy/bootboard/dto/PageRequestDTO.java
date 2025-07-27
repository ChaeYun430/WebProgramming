package com.lcy.bootboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    // 기본값 설정
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; //검색의 종류 : t, c, w, tc, tw, tcw

    private String keyword;

    //문자열인 검색 조건의 배열화
    public String[] getTypes(){
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    //Pageable 타입 반환
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }

    //검색 조건과 페이징 조건을 문자열로 구성
    private String link;

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);
            if (type != null || type.length() > 0) {
                builder.append("&type="+type);
            }
            if (keyword != null) {
                try{
                    builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
                }catch (UnsupportedEncodingException e){
                }
            }
        }
        return link;
    }
}
