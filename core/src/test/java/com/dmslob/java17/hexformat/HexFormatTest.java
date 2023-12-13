package com.dmslob.java17.hexformat;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HexFormatTest {

    @Test
    public void should_format_byte_to_string() {
        // given
        // For example, an individual byte is converted to a string of
        // hexadecimal digits using toHexDigits(int) and converted from a string to a
        // primitive value using fromHexDigits(string)
        var hex = HexFormat.of();
        byte b = 127;
        // when
        String byteStr = hex.toHexDigits(b);
        byte byteVal = (byte) HexFormat.fromHexDigits(byteStr);
        // then
        assert(byteStr.equals("7f"));
        assert(b == byteVal);
    }

    @Test
    public void should_format_bytes_to_string_with_delimiter() {
        // given
        // The formatted string is: "00:01:02:03:7C:7D:7E:7F"
        // For a fingerprint of byte values that uses the delimiter colon ( ":")
        // and uppercase characters the HexFormat is:
        var formatFingerprint = HexFormat
                .ofDelimiter(":")
                .withUpperCase();
        byte[] bytes = {0, 1, 2, 3, 124, 125, 126, 127};
        // when
        String str = formatFingerprint.formatHex(bytes);
        byte[] parsed = formatFingerprint.parseHex(str);
        // then
        assertThat(str).isEqualTo("00:01:02:03:7C:7D:7E:7F");
        assert(Arrays.equals(bytes, parsed));
    }

    @Test
    public void should_format_bytes_to_string_with_prefix() {
        // given
        // The formatted string is: "#00, #01, #02, #03, #7c, #7d, #7e, #7f"
        // For a comma ( ", ") separated format with a prefix ( "#") using lowercase
        // hex digits the HexFormat is:
        var commaFormat = HexFormat
                .ofDelimiter(", ")
                .withPrefix("#");
        byte[] bytes = {0, 1, 2, 3, 124, 125, 126, 127};
        // when
        String str = commaFormat.formatHex(bytes);
        byte[] parsed = commaFormat.parseHex(str);
        // then
        assertThat(str).isEqualTo("#00, #01, #02, #03, #7c, #7d, #7e, #7f");
        assert(Arrays.equals(bytes, parsed));
    }
}
