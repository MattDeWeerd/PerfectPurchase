package com.info5059.casestudy.purchaseorder;

import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.product.QRCodeGenerator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PurchaseOrderPDFGenerator - a class for creating dynamic product
 * purchaseOrder output in
 * PDF format using the iText 7 library
 *
 * @author Evan
 */
public abstract class PurchaseOrderPDFGenerator extends AbstractPdfView {
        public static ByteArrayInputStream generatePurchaseOrder(String poid,
                        PurchaseOrderRepository purchaseOrderRepository,
                        VendorRepository vendorRepository,
                        ProductRepository productRepository)
                        throws IOException {
                URL imageUrl = PurchaseOrderPDFGenerator.class.getResource("/static/assets/images/store-logo.png");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter writer = new PdfWriter(baos);

                // Initialize PDF document to be written to a stream not a file
                PdfDocument pdf = new PdfDocument(writer);

                // Document is the main object
                Document document = new Document(pdf);
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

                // add the image to the document
                Image img = new Image(ImageDataFactory.create(imageUrl)).scaleAbsolute(230, 127.5f)
                                .setFixedPosition(10, 700);
                document.add(img);

                Locale locale = new Locale("en", "US");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
                try {
                        document.add(new Paragraph("\n"));
                        Optional<PurchaseOrder> optPurchaseOrder = purchaseOrderRepository
                                        .findById(Long.parseLong(poid));
                        if (optPurchaseOrder.isPresent()) {

                                PurchaseOrder purchaseOrder = optPurchaseOrder.get();
                                document.add(new Paragraph("PurchaseOrder\n#" +
                                                poid).setFont(font).setFontSize(18).setBold()
                                                .setMarginLeft(210)
                                                .setTextAlignment(TextAlignment.CENTER));
                                document.add(new Paragraph("\n\n"));

                                Optional<Vendor> optVendor = vendorRepository.findById(purchaseOrder.getVendorid());
                                // Vendor details
                                if (optVendor.isPresent()) {

                                        Vendor vend = optVendor.get();

                                        Table table = new Table(2);
                                        table.setWidth(new UnitValue(UnitValue.PERCENT, 20));

                                        // table details
                                        Cell cell = new Cell().add(new Paragraph("Vendor:")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER);
                                        ;
                                        table.addCell(cell);

                                        cell = new Cell().add(new Paragraph(vend.getName())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph("")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)

                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vend.getAddress1())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph("")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vend.getCity())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph("")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vend.getProvince())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph("")
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER);
                                        ;

                                        table.addCell(cell);
                                        cell = new Cell().add(new Paragraph(vend.getEmail())
                                                        .setFont(font)
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setTextAlignment(TextAlignment.LEFT))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                                        ;

                                        table.addCell(cell);

                                        document.add(table);
                                        document.add(new Paragraph("\n\n"));
                                }

                                // PurchaseOrder details
                                Table table = new Table(5);
                                table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
                                // Unfortunately we must format each cell individually :(
                                // table headings
                                Cell cell = new Cell().add(new Paragraph("Product Code")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Description")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Qty Sold")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold())
                                                .setTextAlignment(TextAlignment.CENTER);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Price")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold()
                                                .setTextAlignment(TextAlignment.CENTER));
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph("Ext. Price")
                                                .setFont(font)
                                                .setFontSize(12)
                                                .setBold()
                                                .setTextAlignment(TextAlignment.CENTER));
                                table.addCell(cell);

                                BigDecimal tot = new BigDecimal(0.0);

                                // Data
                                for (PurchaseOrderLineitem item : purchaseOrder.getItems()) {

                                        Optional<Product> optPro = productRepository.findById(item.getProductid());
                                        if (optPro.isPresent()) {
                                                Product product = optPro.get();

                                                cell = new Cell().add(new Paragraph("" + item.getProductid())
                                                                .setFont(font)
                                                                .setFontSize(12)
                                                                .setTextAlignment(TextAlignment.CENTER));
                                                table.addCell(cell);

                                                cell = new Cell().add(new Paragraph(product.getName())
                                                                .setFont(font)
                                                                .setFontSize(12)
                                                                .setTextAlignment(TextAlignment.CENTER));
                                                table.addCell(cell);

                                                cell = new Cell().add(new Paragraph("" + item.getQty())
                                                                .setFont(font)
                                                                .setFontSize(12)
                                                                .setTextAlignment(TextAlignment.RIGHT));
                                                table.addCell(cell);

                                                cell = new Cell().add(
                                                                new Paragraph(formatter.format(product.getCostprice()))
                                                                                .setFont(font)
                                                                                .setFontSize(12)
                                                                                .setTextAlignment(TextAlignment.RIGHT));
                                                table.addCell(cell);

                                                cell = new Cell().add(
                                                                new Paragraph(formatter
                                                                                .format((product.getCostprice()
                                                                                                .multiply(BigDecimal
                                                                                                                .valueOf(item.getQty())))))
                                                                                .setFont(font)
                                                                                .setFontSize(12)
                                                                                .setTextAlignment(TextAlignment.RIGHT));
                                                table.addCell(cell);

                                                tot = tot.add(product.getCostprice()
                                                                .multiply(BigDecimal.valueOf(item.getQty())),
                                                                new MathContext(8, RoundingMode.UP));
                                        }

                                }

                                // purchaseOrder total
                                cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell().add(new Paragraph(formatter.format(tot)))
                                                .setTextAlignment(TextAlignment.RIGHT)
                                                .setBackgroundColor(ColorConstants.YELLOW);
                                table.addCell(cell);

                                cell = new Cell(1, 4).add(new Paragraph("Tax:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell().add(
                                                new Paragraph(formatter.format(tot.multiply(BigDecimal.valueOf(0.13)))))
                                                .setTextAlignment(TextAlignment.RIGHT)
                                                .setBackgroundColor(ColorConstants.YELLOW);
                                table.addCell(cell);

                                cell = new Cell(1, 4).add(new Paragraph("PO Total:"))
                                                .setBorder(Border.NO_BORDER)
                                                .setTextAlignment(TextAlignment.RIGHT);
                                table.addCell(cell);
                                cell = new Cell()
                                                .add(new Paragraph(formatter.format(
                                                                tot.add(tot.multiply(BigDecimal.valueOf(0.13))))))
                                                .setTextAlignment(TextAlignment.RIGHT)
                                                .setBackgroundColor(ColorConstants.YELLOW);
                                table.addCell(cell);

                                document.add(table);

                                document.add(new Paragraph("\n\n"));

                        }

                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyy-MM-dd h:mm a");
                        document.add(new Paragraph(
                                        purchaseOrderRepository.findById(Long.parseLong(poid)).get().getPodate()
                                                        .format(formatter2))
                                        .setTextAlignment(TextAlignment.CENTER));

                        if (optPurchaseOrder.isPresent()) {
                                PurchaseOrder purchaseOrder = optPurchaseOrder.get();
                                Optional<Vendor> optVendor = vendorRepository.findById(purchaseOrder.getVendorid());
                                if (optVendor.isPresent()) {
                                        Vendor vend = optVendor.get();
                                        Image qrcode = addSummaryQRCode(vend, purchaseOrder);
                                        document.add(qrcode);
                                }
                        }

                        document.close();
                } catch (

                Exception ex) {
                        Logger.getLogger(PurchaseOrderPDFGenerator.class.getName()).log(Level.SEVERE,
                                        null, ex);
                }
                // finally send stream back to the controller
                return new ByteArrayInputStream(baos.toByteArray());
        }

        static Image addSummaryQRCode(Vendor vend, PurchaseOrder po) {

                Locale locale = new Locale("en", "US");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
                DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd h:mm a");

                QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();

                byte[] qrcodebin = qrCodeGenerator.generateQRCode(

                                "Summary for Purchase Order:" + po.getId() + "\nDate:"
                                                + dFormatter.format(po.getPodate()) + "\nVendor:"
                                                + vend.getName()
                                                + "\nTotal:" + formatter.format(po.getAmount())

                );

                return new Image(ImageDataFactory.create(qrcodebin)).scaleAbsolute(100, 100).setFixedPosition(460, 60);

        }
}