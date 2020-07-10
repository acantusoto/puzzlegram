package com.example.puzzlegram.fragments;

import android.util.Log;

import com.example.puzzlegram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostFragment{

    @Override
    protected void loadNextDataFromApi() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.whereLessThan(Post.KEY_CREATED_AT,allPosts.get(allPosts.size()-1).getCreatedAt());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue getting posts",e );
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "Post: "+ post.getDescription() + ", username: "+ post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                postsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue getting posts",e );
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "Post: "+ post.getDescription() + ", username: "+ post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                postsAdapter.notifyDataSetChanged();
            }
        });
    }
}