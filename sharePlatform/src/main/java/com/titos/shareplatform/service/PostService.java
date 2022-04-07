package com.titos.shareplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.titos.info.global.CommonResult;
<<<<<<< Updated upstream
import com.titos.info.shareplatform.entity.Post;
=======
>>>>>>> Stashed changes
import com.titos.info.shareplatform.vo.PostVO;
import com.titos.info.shareplatform.vo.SharePlatformVO;
import com.titos.info.user.vo.TalentVO;
import com.titos.tool.token.CustomStatement;

import java.util.List;

/**
 * @ClassName PostService
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:51
 **/
public interface PostService extends IService<Post> {

    /**
     * 分页查询所有的帖子
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    CommonResult<List<SharePlatformVO>> listSharePlatform(Integer pageNum, Integer pageSize);

    /**
     * 查询活跃达人
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return 结果
     */
    CommonResult<List<TalentVO>> listTalent(Integer pageNum, Integer pageSize);

    /**
     * 新建帖子
     *
<<<<<<< Updated upstream
     * @param customStatement 用户信息
     * @param postVO          帖子信息
     * @return 新建结果
     */
    CommonResult<Boolean> addPost(CustomStatement customStatement, PostVO postVO);

    /**
     * 批量删除帖子
     *
     * @param postIdList 帖子列表
     * @return 删除是否成功
     */
    CommonResult<Boolean> deletePosts(List<Integer> postIdList);
=======
     * @param postVO 帖子信息
     */
    void addPost(PostVO postVO);

>>>>>>> Stashed changes
}
