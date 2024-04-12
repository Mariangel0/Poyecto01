package org.example.proyecto_01.presentation.facturas;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import jakarta.servlet.http.HttpServletResponse;
import org.example.proyecto_01.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import com.itextpdf.kernel.colors.Color;


import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@org.springframework.stereotype.Controller("facturas")
@SessionAttributes({"facturas", "proveedor", "facturaBusqueda"})
public class controller {

    @Autowired
    private Service service;
    @ModelAttribute("facturas") public Iterable<Factura> facturas(){return new ArrayList<Factura>();}
    @ModelAttribute("proveedor") public Proveedor proveedor(){return new Proveedor();}
    @ModelAttribute("facturaBusqueda") public Factura facturaBusqueda(){return new Factura();}

    @GetMapping("/presentation/facturas/show")
    public String show(Model model, @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor) {
        model.addAttribute("facturas", service.facturasFindAll(proveedor));
        model.addAttribute("facturaBusqueda", new Factura());
        return "/presentation/facturas/Vista";
    }

    @GetMapping("/presentation/facturas/pdf")
    public void pdf(Factura facturaN, HttpServletResponse response) throws IOException {
        Factura factura = service.facturaRead(facturaN.getCodigo());
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=factura.pdf");

        // Configurar estilo para el marco
        Style frameStyle = new Style()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER);

        document.add(new Paragraph("Factura").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER).setMarginBottom(10));
        document.add(new Paragraph("Código: " + factura.getCodigo()).addStyle(frameStyle));
        document.add(new Paragraph("Total: " + factura.getTotal()).addStyle(frameStyle));
        document.add(new Paragraph("Cliente: " + factura.getClienteByClienteNum().getNombre()).addStyle(frameStyle));
        document.add(new Paragraph("Proveedor: " + factura.getProveedorByProveedorIdF().getNombre()).addStyle(frameStyle));
        document.add(new Paragraph("Fecha: " + factura.getFecha()).addStyle(frameStyle));

        document.add(new Paragraph("Detalles de la factura:").setMarginTop(20).setMarginBottom(10).setBold());
        for (Detalle detalle : service.detalles(factura.getCodigo())) {
            Paragraph detalleParagraph = new Paragraph()
                    .add(new Text("-----------------------").setFontSize(8))
                    .add(new Paragraph("Producto: " + detalle.getProductoByProductoCodD().getNombre()).addStyle(frameStyle))
                    .add(new Paragraph(" Cantidad: " + detalle.getCantidad()).addStyle(frameStyle))
                    .add(new Paragraph(" Precio: " + detalle.getProductoByProductoCodD().getPrecio()).addStyle(frameStyle))
                    .add(new Paragraph(" Subtotal: " + detalle.getMonto()).addStyle(frameStyle))
                    .add(new Text("-----------------------").setFontSize(8));
            document.add(detalleParagraph);
        }

        document.close();
    }


    @GetMapping("presentation/facturas/xml")
    public void xml(Factura facturaNumero, HttpServletResponse res) throws Exception {
        Factura factura = service.facturaRead(facturaNumero.getCodigo());
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix( ".xml");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("factura", factura);
        String xml = engine.process("/presentation/facturas/XmlView", ctx);
        res.setContentType("application/xml");
        PrintWriter writer = res.getWriter();
        writer.print(xml);
        writer.close();
    }

}
