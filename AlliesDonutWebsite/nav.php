    <nav>
      <a class="<?php 
      if ($path_parts['filename'] == "index"){
                print 'active';
            }
      ?>" href="index.php">Home</a>

      <a class="<?php
      if ($path_parts['filename'] == "menu"){
                print 'active';
            }?>" href="menu.php">Menu</a>

      <a class="<?php
      if ($path_parts['filename'] == "history"){
                print 'active';
            }?>" href="history.php">History</a>
      <!-- <a class="<?php
      if ($path_parts['filename'] == "nowHiring"){
                print 'active';
            }?>" href="nowHiring.php">Now Hiring</a>
    -->

      <a class="<?php 
      if ($path_parts['filename'] == "cakeOrder"){
                print 'active';
            }?>" href="cakeOrder.php">Cakes</a>
<!--
      <a class="<?php
      if ($path_parts['filename'] == "orderOnline"){
                print 'active';
            }?>" id="online" href="orderOnline.php">Order Online!</a>
      -->
    </nav>