package com.jangbo.api.controller;

import com.jangbo.api.request.ItemRegisterPostReq;
import com.jangbo.api.request.StoreEditPatchReq;
import com.jangbo.api.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value="상품API",tags={"상품"})
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/{storeNo}")
    @ApiOperation(value="상품 등록",notes = "상점에서 판매하는 상품을 등록한다.",httpMethod = "POST")
    public ResponseEntity<Integer> save(@RequestBody ItemRegisterPostReq itemRegisterPostReq){
        Integer savedItemNo = itemService.save(itemRegisterPostReq);
        return new ResponseEntity<Integer>(savedItemNo,HttpStatus.CREATED);
    }

    /* 아이템 수정 */
    @PatchMapping("/{itemNo}")
    @ApiOperation(value = "상품 수정 api", notes="상품 정보 수정, 바뀐 부분만 업데이트 해준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> update(@PathVariable("itemNo") Integer itemNo,@RequestBody ItemRegisterPostReq itemRegisterPostReq) {
        Integer updatedItemNo = itemService.update(itemNo,itemRegisterPostReq);
        return new ResponseEntity<Integer>(updatedItemNo, HttpStatus.CREATED);
    }


    /*아이템 삭제*/
    @DeleteMapping("/{itemNo}")
    @ApiOperation(value = "상품 정보 삭제 api", notes="상품번호로 상품정보삭제",httpMethod = "DELETE")
    public ResponseEntity<Integer> delete(@PathVariable("itemNo") Integer itemNo) {

        itemService.delete(itemNo);

        return new ResponseEntity<Integer>(itemNo, HttpStatus.NO_CONTENT);
    }
}
