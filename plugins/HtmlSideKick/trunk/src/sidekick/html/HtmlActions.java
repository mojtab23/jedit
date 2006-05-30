package sidekick.html;

import java.awt.Point;

import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.buffer.JEditBuffer;
import org.gjt.sp.jedit.textarea.*;
import sidekick.*;
import sidekick.html.parser.html.*;

public class HtmlActions {

	/**
	 * Moves the caret to the matching tag.    
	 */
    public static void matchTag( View view, JEditTextArea textArea ) {
        try {
            SideKickParsedData data = SideKickParsedData.getParsedData( view );
            int cp = textArea.getCaretPosition();
            HtmlAsset asset = ( HtmlAsset ) data.getAssetAtOffset( cp );
            HtmlDocument.HtmlElement element = asset.getHtmlElement();
            if ( element instanceof HtmlDocument.TagBlock ) {
                HtmlDocument.TagBlock block = ( HtmlDocument.TagBlock ) element;
                HtmlDocument.Tag start_tag = block.startTag;
                HtmlDocument.EndTag end_tag = block.endTag;
                if ( cp >= start_tag.getStartPosition().getOffset() && cp <= start_tag.getEndPosition().getOffset() ) {
                    // caret is in start tag, jump to end tag. Need to add 1 to go just past the closing >.
                    textArea.setSelection( new Selection.Range( end_tag.getStartPosition().getOffset(), end_tag.getEndPosition().getOffset() + 1 ) );
                    textArea.moveCaretPosition( end_tag.getEndPosition().getOffset() + 1 );
                }
                else if ( cp >= end_tag.getStartPosition().getOffset() && cp <= end_tag.getEndPosition().getOffset() ) {
                    // caret is in end tag, jump to end tag
                    textArea.setSelection( new Selection.Range( start_tag.getStartPosition().getOffset(), start_tag.getEndPosition().getOffset() + 1 ) );
                    textArea.moveCaretPosition( start_tag.getStartPosition().getOffset() );
                }
                else {
                    textArea.getToolkit().beep();
                }
            }
        }
        catch ( Exception e ) {
            // ignore
        }
    }
}
