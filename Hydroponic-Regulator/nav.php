<nav>
    <a class="<?php
        if ($path_parts['filename'] == 'index') {
            print 'activePage';
        }
        ?>" href="index.php">Home</a>
                
    <a class="<?php
        if ($path_parts['filename'] == 'info') {
            print 'activePage';
        }
        ?>" href="info.php">Background Info</a>

    <a class="<?php
        if ($path_parts['filename'] == 'detail') {
            print 'activePage';
        }
        ?>" href="detail.php">Project Details</a>

    <a class="<?php
        if ($path_parts['filename'] == 'control') {
            print 'activePage';
        }
        ?>" href="control.php">Control</a>
</nav>