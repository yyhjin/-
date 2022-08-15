package com.jangbo.api.controller;

import com.jangbo.api.request.ItemEditPatchReq;
import com.jangbo.api.request.ItemRecentPatchReq;
import com.jangbo.api.request.ItemRegisterPostReq;
import com.jangbo.api.service.ItemService;
import com.jangbo.db.entity.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value="상품API",tags={"상품"})
@RestController
@RequestMapping("/be/item")
@RequiredArgsConstructor

public class ItemController {


    private final ItemService itemService;

    /*아이템 목록 조회*/
    @GetMapping("/store/{storeNo}")
    @ApiOperation(value = "한 상점안에 아이템 목록 api", notes="전체 조회(라이브상점에 뿌릴거임)",httpMethod = "GET")
    public ResponseEntity<List<Item>> findItems(@PathVariable("storeNo") Integer storeNo) {
        List<Item> itemList = itemService.findItemsByStore(storeNo);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /* 아이템 등록 */
    @PostMapping("/{storeNo}")
    @ApiOperation(value="상품 등록",notes = "상점에서 판매하는 상품을 등록한다.",httpMethod = "POST")
    public ResponseEntity<Integer> save(@RequestBody ItemRegisterPostReq itemRegisterPostReq){
        Integer savedItemNo = itemService.save(itemRegisterPostReq);
        return new ResponseEntity<>(savedItemNo,HttpStatus.CREATED);
    }

    /* 아이템 수정 */
    @PatchMapping("/{itemNo}")
    @ApiOperation(value = "상품 수정 api", notes="상품 정보 수정, 바뀐 부분만 업데이트 해준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> update(@PathVariable("itemNo") Integer itemNo,@RequestBody ItemEditPatchReq itemEditPatchReq) {
        Integer updatedItemNo = itemService.update(itemNo,itemEditPatchReq);
        return new ResponseEntity<>(updatedItemNo, HttpStatus.NO_CONTENT);
    }

    /* 최근 사용여부 체크 */
    @PatchMapping("/check/{itemNo}")
    @ApiOperation(value = "최근사용여부 api", notes="상점 오픈할때 사용된 상품들은 최근 사용 true로 바꿔준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> recentUse(@PathVariable("itemNo") Integer itemNo, @RequestBody ItemRecentPatchReq itemRecentPatchReq) {
        Integer updatedItemNo = itemService.isRecent(itemNo,itemRecentPatchReq);
        return new ResponseEntity<>(updatedItemNo,HttpStatus.NO_CONTENT);
    }


    /*아이템 삭제*/
    @DeleteMapping("/{itemNo}")
    @ApiOperation(value = "상품 정보 삭제 api", notes="상품번호로 상품정보삭제",httpMethod = "DELETE")
    public ResponseEntity<Integer> delete(@PathVariable("itemNo") Integer itemNo) {

        itemService.delete(itemNo);

        return new ResponseEntity<>(itemNo, HttpStatus.NO_CONTENT);
    }


    /*아이템 조회*/
    @GetMapping("/{itemNo}")
    @ApiOperation(value="상품 조회 api",notes = "상품번호로 상품정보를 조회한다.",httpMethod = "GET")
    public ResponseEntity<Item> selectByItemNo(@PathVariable("itemNo") Integer itemNo) {
        Item item = itemService.findItemByItemNo(itemNo);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


}
