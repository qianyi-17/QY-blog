package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.dto.TagListDto;
import com.qianyi.domain.entity.Tag;
import com.qianyi.domain.vo.PageVo;
import com.qianyi.domain.vo.TagVo;
import com.qianyi.service.TagService;
import com.qianyi.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 千亦
 * @create 2022-11-06-15:32
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;


    /**
     * 查询所有标签接口
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        ResponseResult<PageVo> result = tagService.pageTagList(pageNum,pageSize,tagListDto);
        return result;
    }

    /**
     * 新增标签接口
     * @param tagListDto
     * @return
     */
    @PostMapping()
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        ResponseResult result = tagService.addTag(tagListDto);
        return result;
    }

    /**
     * 删除标签接口
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        ResponseResult result = tagService.deleteTag(id);
        return result;
    }

    @PutMapping
    public ResponseResult edit(@RequestBody TagVo tagVo){
        Tag tag = BeanCopyUtils.copyBean(tagVo, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }
    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable(value="id") Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }
//    /**
//     * 修改标签
//     * @return
//     */
//    @GetMapping("/{id}")
//    public ResponseResult<TagVo> getTagInfo(@PathVariable Long id){
//        ResponseResult result = tagService.getTagInfo(id);
//        return result;
//    }
//    @PutMapping()
//    public ResponseResult putTag(@RequestBody TagVo tagVo){
//        ResponseResult result= tagService.putTag(tagVo);
//        return result;
//    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
       ResponseResult result = tagService.listAllTag();
       return result;
    }


}
