/*
 *  Copyright (c) 2009, Eric Berry <elberry@gmail.com>
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification,
 *  are permitted provided that the following conditions are met:
 *
 *  	* Redistributions of source code must retain the above copyright notice, this
 *  	  list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright notice,
 *  	  this list of conditions and the following disclaimer in the documentation
 *  	  and/or other materials provided with the distribution.
 *  	* Neither the name of the Organization (TellurianRing.com) nor the names of
 *  	  its contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package projectbuilder.builder

/**
 *
 * @author eberry
 */
public class DirectoryFactory extends AbstractFactory {

    private file

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        println("DirectoryFactory - builder: ${builder} | name: ${name} | value: ${value} | attributes: ${attributes} | parent: ${builder.getCurrent()}")
        def parentFile = builder.getCurrent()
        def directory = new File(value)
        if(parentFile != null) {
            directory = new File(parentFile, value)
        }
        def directoryNode = new DirectoryFactory()
        directoryNode.file = directory
        return directory
    }

    public boolean isLeaf() {
        return false
    }

    void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {
        if(!node.exists()) {
            node.mkdir()
            println("DirectoryFactory - creating directory: ${node.path}")
        }
    }
}

