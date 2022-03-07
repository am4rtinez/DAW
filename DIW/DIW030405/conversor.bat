@echo off
@REM Set variables.
SET DIR=%1
SET EXT_IN=%2
SET EXT_OUT=%3

echo.
echo -------------------------------------------------------------------------
echo  *** Script audio convert for parameter ***
echo -------------------------------------------------------------------------
echo.
echo  * Input extension: %EXT_IN%
echo  * Output extension: %EXT_OUT%
echo.
echo  AUTHOR: Angel Martinez
echo -------------------------------------------------------------------------
echo.
echo Selected directory is %DIR%
echo Input extension selected is %EXT_IN%
echo Outputd extension selected is %EXT_OUT%
echo.

for %%f in (%DIR%\*.%EXT_IN%) do (
    echo -------------------------------------------------------------------------
    echo  * File name: %%~nf 
    echo -------------------------------------------------------------------------
    echo.
    echo * Input file: %%f
    echo * Output file: %DIR%\%%~nf.%EXT_OUT%
    echo.
    echo Conversion..
    ffmpeg -i %%f -vn -ar 44100 -ac 2 -b:a 192k %DIR%\%%~nf.%EXT_OUT%
    echo.
    echo -------------------------------------------------------------------------
    echo.
)

@REM -i - input file
@REM -vn - Disable video, to make sure no video (including album cover image) is included if the source would be a video file
@REM -ar - Set the audio sampling frequency. For output streams it is set by default to the frequency of the corresponding input stream. For input streams this option only makes sense for audio grabbing devices and raw demuxers and is mapped to the corresponding demuxer options.
@REM -ac - Set the number of audio channels. For output streams it is set by default to the number of input audio channels. For input streams this option only makes sense for audio grabbing devices and raw demuxers and is mapped to the corresponding demuxer options. So used here to make sure it is stereo (2 channels)
@REM -b:a - Converts the audio bitrate to be exact 192kbit per second

@REM Clear variables
SET DIR=
SET EXT_IN=
SET EXT_OUT=