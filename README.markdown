# DropEdit

## Git Branches Management

Keep only production-ready code in `master`. The main codebase used should be `develop`, with specific branches for each feature being worked on with the naming convention `feature/<name>`. Bug fixes go in `bug/<name>` and any hotfixes go in `hotfix`. Once a branch has been merged in, remove it for cleanliness. The general workflow should be something like this:
  
    $ git checkout -b feature/auth            # checkout (move into) a new branch (using the -b) called feature/auth
    $ ...                                     # do your edits
    $ git add .                               # track your changes
    $ git commit -m "a useful message"        # what you did and why
    $ git push origin feature/auth            # keep the github repo up to date
    $ ...                                     # lets say you're done with this feature
    $ git checkout develop                    # move to develop to merge it in
    $ git merge feature/auth --no-ff          # merge code in, forcing no fast forward (for cleanliness)
    $ git branch -d feature/auth              # remove local branch
    $ git push origin :feature/auth           # remove remote branch
    $ git push origin develop                 # keep the github repo up to date
    
To start out, you will need to clone the repo

Testing git - hello world!
