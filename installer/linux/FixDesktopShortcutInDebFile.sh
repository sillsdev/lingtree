#! /usr/bin/bash
if [ -d debtemp ]; then
 echo "	removing debtemp"
 rm -rf debtemp
fi
echo "	fixing desktop shortcut in deb file"
mkdir debtemp
DEB_FILE=`ls lingtree_1.0-1_amd64.deb`
dpkg-deb -R $DEB_FILE debtemp
sed '/^Exec=*/a Path=/opt/sil/lingtree/bin/' debtemp/opt/sil/lingtree/lib/lingtree-LingTree.desktop >debtemp/opt/sil/lingtree/lib/lingtree-LingTree2.desktop
rm debtemp/opt/sil/lingtree/lib/lingtree-LingTree.desktop
mv debtemp/opt/sil/lingtree/lib/lingtree-Lingtree2.desktop debtemp/opt/sil/lingtree/lib/lingtree-LingTree.desktop
dpkg-deb -b debtemp $DEB_FILE.deb
echo "	renaming fixed up deb file"
rm $DEB_FILE
mv $DEB_FILE.deb $DEB_FILE

