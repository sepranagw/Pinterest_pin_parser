# Pinterest_pin_parser
Sample app that receives data via the public pinterest api, parses it for a user's images and descriptions, then displays it.

## User Interface:

Splash Page

![Splash page](http://i1280.photobucket.com/albums/a495/Corinne_Cameron_Nakashima/android%20apps/Screenshot_2015-07-27-20-45-33_zpsmenamhmd.png)

User's pinned images

![Splash page](https://dl.dropboxusercontent.com/u/42465091/website/images/pinuser_grid.png)

Individual pin with description

![Splash page](https://dl.dropboxusercontent.com/u/42465091/website/images/pin_individ.png)

## Features:

1- Error catching with Toast informer for: blank (null) entry, non-existent user name entry, general system/server error

2- Name reformatting: removes spaces, converts to lower-case in order to form API-callable URL

3- Auto-clear of EditText contents, when you wish to re-enter a user name.

4- Uses Picasso to fetch pin image URLs and display them

5- Uses Gridview (not Tablelayout) for pin image displays

6- All pin images in Gridview are clickable and in most cases, display a larger image version with description

7-Individual pin image display with description are scrollable, in case screen isn't big enough to display

## Things to work on:

1- For some reason, cannot get back arrow button (to parent, grid-of-pin-images activity) to work correctly on individual pin display activity.  Crashes if implemented, so it's not implemented.

2- For pinned images that are not always uniform in size with respect to eachother, when displayed on individual pin activity page, will tend to sit on left side of page for a reason I haven't figured out yet.

3- Code for other types of display formatting (javascript? something else?) gets through into descriptions, appearing as '&3456' for example.  Should implement code to parse and remove these.

4- Sometimes image displayed in individual pin display is *not* displayed larger, but only as the same size as in the grid.  This is due to non-uniform image size.  Martha Stewart media formatters, you are awesome for formatting all your pins so uniformly in size 
^_^ Livin' up to that Martha Stewart name quality - all your pins display ideally in my app!
