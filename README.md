# Rainbow Wood
[![discord-singular](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-singular_vector.svg)](https://discord.offsetmonkey538.top/)
[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/rainbow-wood)  
[![Requires Fabric API](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/requires/fabric-api_vector.svg)](https://modrinth.com/mod/fabric-api)

**Adds new tintable variants for all vanilla wooden items.**  
In case the 11 wood types from vanilla aren't enough, now you can have ~16.5 million different ones!¹  
###### ¹ One for each possible RGB color, but most of them look the same and thus are pointless haha

<details>

<summary>Full list of new tintable items</summary>

- Log
- Stripped Log
- Wood
- Stripped Wood
- Planks
- Stairs
- Slab
- Fence Gate
- Fence Gate
- Door
- Trapdoor
- Pressure Plate
- Button
- Sign
- Hanging Sign
- Hanging Sign
- Boat
- Chest Boat

</details>

Video of me messing around with the mod with no real planning whatsoever and oh god it's like 10 minutes long why would anyone even click on this why did I do this oh god:  
<iframe src="https://www.youtube.com/embed/e6QHht78zMs"></iframe>

## Acquiring
In creative mode, just open the creative menu and there are 76 different colors available in the "Rainbow Wood" tab.

When playing survival, you first have to get *uncolored* items before coloring them. To do that, you can drop any normal wooden item into a cauldron filled with water.  
You can also craft colored items from other colored items (i.e. stairs from planks, fences from planks and sticks), just make sure that the inputs all have the same color.  
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/purple-fence.png" alt="Image depicting 3x3 crafting recipe where the first row is empty and the bottom two rows contain the same squence, which is as follows: slot 1 contains dark purple colored planks, slot 2 contains a stick and slot 3 contains dark purple colored planks. The result is 3 dark purple colored fences, with the exact RGB value of 127, 0, 127">


<br>

Colors can be applied in two ways:
### Averaging
This is the default way. It gets the average color of the input items, and then applied it to the output.  
The inputs can contain other tinted items of the same type (i.e. you can't combine stairs with a slab) or dyes.  
Examples:

<details>
<summary>Red Dye + Blue Dye + Uncolored Planks</summary>
<p>
Coloring planks with a Red Dye (RGB of 255, 0, 0) and Blue Dye (RGB of 0, 0, 255) results in colored planks with the color of (127, 0, 127) as that is the average.
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/redd-bluedd-uncoloredp-avg.png" alt="Image depicting 2x2 crafting recipe where the first slot is uncolored planks, second slot is red dye, third slot is blue dye and fourth slot is empty. The result is dark purple colored planks, with the exact RGB value of 127, 0, 127">
</details>

<details>
<summary>Red Dye + Uncolored Planks</summary>
<p>
Coloring planks with a Red Dye (RGB of 255, 0, 0) results in colored planks with the color of (255, 0, 0) aka red.
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/redd-uncoloredp-avg.png" alt="Image depicting 2x2 crafting recipe where the first slot is uncolored planks, second slot is red dye, third slot is empty and fourth slot is empty. The result is red colored planks, with the exact RGB value of 255, 0, 0">
</details>

<details>
<summary>Blue Dye + Uncolored Planks</summary>
<p>
Coloring planks with a Blue Dye (RGB of 0, 0, 255) results in colored planks with the color of (0, 0, 255) aka blue.
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/blued-uncoloredp-avg.png" alt="Image depicting 2x2 crafting recipe where the first slot is uncolored planks, second slot is blue dye, third slot is empty and fourth slot is empty. The result is blue colored planks, with the exact RGB value of 0, 0, 255">
</details>

<details>
<summary>Red Planks + Blue Planks</summary>
<p>
Combining red planks (RGB of 255, 0, 0) with blue planks (RGB of 0, 0, 255) results in 2 colored planks with the color of (127, 0, 127) as that is the average.
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/redp-bluep-avg.png" alt="Image depicting 2x2 crafting recipe where the first slot is Red planks, second slot is Blue Planks, third slot is empty and fourth slot is empty. The result is 2 dark purple colored planks, with the exact RGB value of 127, 0, 127">
</details>

<details>
<summary>Purple Planks + Uncolored Planks</summary>
<p>
It is also possible to combine uncolored items with colored items, which will result in duplication of the colored items.
Combining purple planks (RGB of 127, 0, 127) with uncolored planks results in 2 colored planks with the color of (127, 0, 127).
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/purplep-uncoloredp-avg.png" alt="Image depicting 2x2 crafting recipe where the first slot is dark purple planks, second slot is uncolored planks, third slot is empty and fourth slot is empty. The result is 2 dark purple colored planks, with the exact RGB value of 127, 0, 127">
</details>

### Adding
To add the RGB values of the inputs, you need to put an **Iron Ingot** into the recipe as a *modifier*.  
Examples:

<details>
<summary>Red Dye + Blue Dye + Uncolored Planks</summary>
<p>
Coloring planks with a Red Dye (RGB of 255, 0, 0) and Blue Dye (RGB of 0, 0, 255) results in colored planks with the color of (255, 0, 255).
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/redd-blued-uncoloredp-add.png" alt="Image depicting 2x2 crafting recipe where the first slot is uncolored planks, second slot is red dye, third slot is blue dye and fourth slot is an iron ingot. The result is magenta colored planks, with the exact RGB value of 255, 0, 255">
</details>

<details>
<summary>Red Planks + Blue Planks</summary>
<p>
Combining red planks (RGB of 255, 0, 0) with blue planks (RGB of 0, 0, 255) results in 2 colored planks with the color of (255, 0, 255).
</p>
<img src="https://github.com/OffsetMods538/Rainbow-Wood/raw/refs/heads/master/images/redp-bluep-add.png" alt="Image depicting 2x2 crafting recipe where the first slot is red planks, second slot is blue planks, third slot is an iron ingot and fourth slot is empty. The result is 2 magenta colored planks, with the exact RGB value of 255, 0, 255">
</details>
