function combine($resourcesFolder, $sourceFolder, $targetFolder, $fileType)
{
    $targetFile = "$targetFolder\all.$fileType"
    if ((Test-Path -Path "$targetFolder" -PathType Leaf))
    {
        del "$targetFile"
    }
    if (!(test-path -PathType container $targetFolder))
    {
        New-Item -ItemType Directory -Path $targetFolder
    }
    dir "$sourceFolder\*" -include "*.$fileType" -rec | gc | out-file $targetFile
}

$resourcesFolder = "$PSScriptRoot\..\src\main\resources"
$jsSource = "$resourcesFolder\rawJs"
$jsTargetFolder = "$resourcesFolder\static\js"

$cssSource = "$resourcesFolder\rawCss"
$cssTargetFolder = "$resourcesFolder\static\css"

combine "$resourcesFolder" "$jsSource" "$jsTargetFolder" "js"
combine "$resourcesFolder" "$cssSource" "$cssTargetFolder" "css"
