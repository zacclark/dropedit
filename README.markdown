# DropEdit

## Git Branches Management

### Start Fresh

To get started fresh:

    # navigate to wherever you want to put dropedit
    $ git clone git@github.com:spyyddir/dropedit.git
    $ git checkout -b develop                             # make a new local branch named develop, and check into it
    $ git pull origin develop                             # get the latest develop code from the origin

Now you have the base master and develop branches, and can start working.

### Working

Before doing any work, make sure your develop is up to date with the latest on Github:

    $ git status                                          # you should see the following message
    # On branch develop
    nothing to commit (working directory clean)
    $ git pull origin develop
    
If the message is different, such as:

    # On branch develop
    # Changed but not updated:
    #   (use "git add <file>..." to update what will be committed)
    #   (use "git checkout -- <file>..." to discard changes in working directory)
    #
    #	modified:   <something>
    #
    no changes added to commit (use "git add" and/or "git commit -a")
    
Then you have done work straight on develop, which you should not do. What might have happened is you were working on another branch, and forgot to stage and commit changes. If this is the case, you should `git checkout <the branch you were working on>` and finishing commiting files there.
  
If you have the proper "working directory clean" message, you should do the following to ensure the latest code:

    $ git pull origin develop
    
Now you can begin:

    $ git checkout -b <name>                  # choose a useful name, like 'feature/editfile' or 'bug/hotfix'
    
This has created a new branch with that name, and checked you into it. Now you can do some work. Once you have reached some milestone, like a part of the feature is working (ex, file list is in the servlet, but not yet in the view), you should make a commit:

    $ git add .                               # this tells git to look at all the files in the current directory, recursively down
    $ git commit -m "A useful message"        # this actually commits the changes, associated with the message
    
If you have deleted any files, you will need to do a `git add -A` just after `git add .`. Keep working, using the add/commit cycle until the feature is complete. Now you should have three branches:

    $ git branch
      develop
    * <the name you chose>
      master
      
The * indicates the branch you are on. At this point, you'll want to move back to develop, so you can merge your feature into it. The following is a good cycle:

    $ git checkout develop                    # move over to using the develop branch
    $ git pull origin develop                 # make sure we have the latest develop code
    $ git merge --no-ff <feature branch name> # this merges your code into develop
    
This will list out the files changes, potentially with conflicts. If there are conflicts, edit those files to make sure they work. RUN THE TESTS. Once you are sure everything is working after the conflict, do an add/commit cycle to track these conflict fixes.

Now, either if you fixed conflicts, or if there were none, you have the latest develop with your changes. Do the following:

    $ git branch -d <feature branch name>     # remove the feature branch you were working in, because the code is in develop now
    $ git push origin develop                 # push your version of develop up to the origin
    
You have now successfully added a feature to the application.

### End of an Iteration Merging to Master

At the end of the iteration, we will want to update `master` with stable code. `develop` should have collected all the different features, and be in a working state.

  $ git checkout master
  $ git merge --no-ff develop -m "Iteration <#>"
  $ git push origin master
  $ git checkout develop
  
### Extra Help
    
If you need more help, read [A Successful Git Branching Model](http://nvie.com/posts/a-successful-git-branching-model/), which goes quite in depth about how to work branches.