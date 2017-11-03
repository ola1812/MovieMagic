package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLSerializer implements Serializer
{

  private Stack stack = new Stack();
  private File file;

  public XMLSerializer(File file)
  {
    this.file = file;
  }

  public void push(Object o)
  {
    stack.push(o);
  }

  public Object pop()
  {
    return stack.pop(); 
  }

  @SuppressWarnings("unchecked")
  public void read() throws Exception
  {
    ObjectInputStream inputFile = null;

    try
    {
    	
      XStream xstream = new XStream(new DomDriver());
      inputFile = xstream.createObjectInputStream(new FileReader(file));
      stack = (Stack) inputFile.readObject();
    }
    finally
    {
      if (inputFile != null)
      {
    	  inputFile.close();
      }
    }
  }

  public void write() throws Exception
  {
    ObjectOutputStream outputFile = null;

    try
    {
      XStream xstream = new XStream(new DomDriver());
      outputFile = xstream.createObjectOutputStream(new FileWriter(file));
      outputFile.writeObject(stack);
    }
    finally
    {
      if (outputFile != null)
      {
        outputFile.close();
      }
    }
  }
}